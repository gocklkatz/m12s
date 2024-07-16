package io.gocklkatz.m12s.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ReadTspLibInstances {

    private static final Logger logger = LoggerFactory.getLogger(ReadTspLibInstances.class);

    public static int[][] readTspLibInstance(String instanceName) {
        int[][] distanceMatrix = new int[0][0];
        try(InputStream is = ReadTspLibInstances.class.getResourceAsStream("/samples/tsp/" + instanceName)) {

            JAXBContext context = JAXBContext.newInstance(TspLibInstance.class);
            TspLibInstance tspLibInstance = (TspLibInstance) context.createUnmarshaller().unmarshal(is);
            System.out.println(tspLibInstance.getName());

        } catch (IOException ioException) {
            logger.info("Problem with inputStream for sample.", ioException);
        } catch (JAXBException jaxbException) {
            logger.info("Problem with JAXB instance creation for sample.", jaxbException);
        }

        return distanceMatrix;
    }
}
