package com.jimbarritt.spikes.parsejava;

import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

public class ScansAFileHierarchyIntegrationTest {

    private static final Logger log = Logger.getLogger(ScansAFileHierarchyIntegrationTest.class);

    @Test
    public void loadsUpASetOfDirectories() throws Exception {
        File testDir = new File(findTempDir(), "parse-java-field-test");
        if (testDir.exists()) {
            testDir.delete();
        }
        testDir.mkdirs();
        FileTreeGenerator generator = new FileTreeGenerator(testDir, 3, 3, 10, "java");
        generator.generateTree();

        FileScanner fileScanner = new FileScanner(testDir, "java");
        fileScanner.scan(new FileScanningAction() {
            @Override
            public void scanFile(File file) {
                log.info(file.getAbsolutePath());
            }
        });

    }

    private static class FileTreeGenerator {
        private static final Logger log = Logger.getLogger(ScansAFileHierarchyIntegrationTest.class);

        private final int depthOfTree;
        private final int maxDirectories;
        private final int maxFiles;
        private final String fileType;
        private final File rootDir;

        public FileTreeGenerator(File rootDir, int depthOfTree, int maxDirectories, int maxFiles, String fileType) {
            this.rootDir = rootDir;
            this.depthOfTree = depthOfTree;
            this.maxDirectories = maxDirectories;
            this.maxFiles = maxFiles;
            this.fileType = fileType;
            log.info("Initialised a file generator in [" + rootDir.getAbsolutePath() + "]");
        }

        public void generateTree() throws IOException {
            generateDirectories(rootDir, 1);
        }

        private void generateDirectories(File parent, int currentDepth) throws IOException {
            if (currentDepth > depthOfTree) {
                return;
            }
            for (int iDir=1;iDir<=maxDirectories;++iDir) {
                File directory = createDirectory(parent, iDir, currentDepth);
                createFilesInDirectory(directory, currentDepth);
                generateDirectories(directory, currentDepth+1);
            }
        }

        private File createDirectory(File parent, int iDir, int currentDepth) {
            File directory =  new File(parent, currentDepth + "-" + iDir + "-directory");
            directory.mkdirs();
            return directory;
        }

        private void createFilesInDirectory(File directory, int currentDepth) throws IOException {
            for (int iFile=1;iFile<=maxFiles;++iFile) {
                File file = new File(directory, currentDepth + "-" + iFile + "-file." + fileType);
                file.createNewFile();
            }
        }
    }

    private static File findTempDir() throws IOException {
        File tempFile = File.createTempFile("foo", "bar");
        tempFile.deleteOnExit();
        return tempFile.getParentFile();
    }

}