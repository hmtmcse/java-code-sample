package archive;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtractTar {

    public static void main(String[] args) throws IOException {

        String sourcePath = "C:\\Users\\touhid\\Desktop\\tmp\\archive.tar\\archive\\sample\\backup.tar\\backup.tar";
        String targetPath = "C:\\Users\\touhid\\Desktop\\tmp\\vaia";
        File targetDir = new File(targetPath);
        InputStream fi = Files.newInputStream(Paths.get(sourcePath));
        InputStream bi = new BufferedInputStream(fi);
//        InputStream gzi = new GzipCompressorInputStream(bi);
//        ArchiveInputStream i = new TarArchiveInputStream(gzi);
        ArchiveInputStream i = new TarArchiveInputStream(bi);

        ArchiveEntry entry = null;
        while ((entry = i.getNextEntry()) != null) {
            if (!i.canReadEntryData(entry)) {
                // log something?
                continue;
            }
            String name = entry.getName();
            File f = new File(targetPath, name);
            if (entry.isDirectory()) {
                if (!f.isDirectory() && !f.mkdirs()) {
                    throw new IOException("failed to create directory " + f);
                }
            } else {
                File parent = f.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("failed to create directory " + parent);
                }
                try (OutputStream o = Files.newOutputStream(f.toPath())) {
                    IOUtils.copy(i, o);
                }
            }
        }


    }

}
