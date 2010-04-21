package com.jimbarritt.spikes.parsejava;

import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

public class LoadsAFileHierarchyTest {


    @Test
    public void loadsUpASetOfDirectories() throws Exception {
        File testDir = new File(findTempDir(), "parse-java-field-test");

        FileTreeGenerator generator = new FileTreeGenerator(testDir, 2, 2, 2);

        generator.generateTree();        

    }

    private static class FileTreeGenerator {
        private static final Logger log = Logger.getLogger(LoadsAFileHierarchyTest.class);

        private final int depthOfTree;
        private final int maxDirectories;
        private final int maxFiles;
        private final File rootDir;

        public FileTreeGenerator(File rootDir, int depthOfTree, int maxDirectories, int maxFiles) {
            this.rootDir = rootDir;
            this.depthOfTree = depthOfTree;
            this.maxDirectories = maxDirectories;
            this.maxFiles = maxFiles;
            log.info("Initialised a file generator in [" + rootDir.getAbsolutePath() + "]");
        }

        public void generateTree() throws IOException {
            generateDirectories(rootDir, 0);
        }

        private void generateDirectories(File parent, int currentDepth) throws IOException {
            if (currentDepth > depthOfTree) {
                return;
            }
            for (int iDir=0;iDir<maxDirectories;++iDir) {
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
            for (int iFile=0;iFile<maxFiles;++iFile) {
                File file = new File(directory, currentDepth + "-file.txt");
                file.createNewFile();
            }
        }
    }

    private static File findTempDir() throws IOException {
        File tempFile = File.createTempFile("foo", "bar");
        tempFile.deleteOnExit();
        File tempDir = tempFile.getParentFile();
        return tempDir;
    }

}