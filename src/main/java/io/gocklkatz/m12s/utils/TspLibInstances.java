package io.gocklkatz.m12s.utils;

import generated.Edge;
import generated.TravellingSalesmanProblemInstance;
import generated.Vertex;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

public class TspLibInstances {

    private static final Logger logger = LoggerFactory.getLogger(TspLibInstances.class);

    private final String fileName;

    private TravellingSalesmanProblemInstance tspInstance;

    public TspLibInstances(String fileName) {
        this.fileName = fileName;
    }

    public double[][] getTspLibInstanceAsArray() {
        loadTspInstance();
        return distanceMatrix();
    }

    private void loadTspInstance() {
        try(InputStream is = TspLibInstances.class.getResourceAsStream("/tsp/samples/" + fileName)) {

            JAXBContext jaxbContext = JAXBContext.newInstance(TravellingSalesmanProblemInstance.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<TravellingSalesmanProblemInstance> root = unmarshaller.unmarshal(new StreamSource(is), TravellingSalesmanProblemInstance.class);
            tspInstance = root.getValue();

        } catch (IOException ioException) {
            logger.info("Problem with inputStream for sample.", ioException);
        } catch (JAXBException jaxbException) {
            logger.info("Problem with JAXB instance creation for sample.", jaxbException);
        }
    }

    private double[][] distanceMatrix() {
        int dim = tspInstance.getGraph().getVertex().size();
        double[][] distanceMatrix = new double[dim][dim];

        for(int i = 0; i < dim; i++){
            Vertex v = tspInstance.getGraph().getVertex().get(i);
            for(Edge e : v.getEdge()) {
                distanceMatrix[i][e.getValue().intValue()] = e.getCost();
            }
        }

        return distanceMatrix;
    }

    public String getFileName() {
        return fileName;
    }

    public TravellingSalesmanProblemInstance getTsp() {
        return tspInstance;
    }
}
