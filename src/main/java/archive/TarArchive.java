package archive;

import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class TarArchive {


    public static void main(String[] args) throws IOException {

        TarArchiveOutputStream out;
        Path source = Paths.get("");

        String outputFile = "OutPut";
        FileOutputStream fileOut = new FileOutputStream(outputFile);
        BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
        GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
        out = new TarArchiveOutputStream(gzOut);


        Files.walkFileTree(source, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return null;
            }
        });

    }

}
