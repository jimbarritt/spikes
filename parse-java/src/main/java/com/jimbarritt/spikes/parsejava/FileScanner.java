package com.jimbarritt.spikes.parsejava;

import java.io.*;

public class FileScanner {
    private static final FileFilter DIRECTORY_FILTER = new DirectoryFilter();
    private final File rootDir;
    private final String fileType;

    public FileScanner(File rootDir, String fileType) {
        this.rootDir = rootDir;
        this.fileType = fileType;
    }

    public void scan(FileScanningAction fileScanningAction) {
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(fileType);
            }
        };

        scanFiles(rootDir, filter, fileScanningAction);
    }

    private void scanFiles(File parentDir, FilenameFilter filter, FileScanningAction fileScanningAction) {
        File[] files = parentDir.listFiles(filter);
        for (File file : files) {
            fileScanningAction.scanFile(file);
        }
        File[] directories = parentDir.listFiles(DIRECTORY_FILTER);
        for(File directory : directories) {
            scanFiles(directory, filter, fileScanningAction);
        }
    }

    private static class DirectoryFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }
}
