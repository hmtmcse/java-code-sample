package nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CopyTree {

    public static void main(String[] args) {
//        Path rootPath = Paths.get("C:\\Users\\touhid\\Desktop\\tmp\\delete");
        Path source = Paths.get("C:\\Users\\touhid\\Desktop\\tmp\\copyFrom");
        Path target = Paths.get("C:\\Users\\touhid\\Desktop\\tmp\\copyTo");

        try {
            Files.walkFileTree(source, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    Path newdir = target.resolve(source.relativize(dir));
                    try {
                        Files.copy(dir, newdir, COPY_ATTRIBUTES);
                    } catch (FileAlreadyExistsException x) {
                        // ignore
                    } catch (IOException x) {
                        System.err.format("Unable to create: %s: %s%n", newdir, x);
                        return SKIP_SUBTREE;
                    }
                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    try {
                        Path newdir = target.resolve(source.relativize(file));
                        Files.copy(file, newdir, REPLACE_EXISTING);
                    } catch (IOException x) {
                        System.err.format("Unable to copy: %s: %s%n", source, x);
                    }

                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    if (exc instanceof FileSystemLoopException) {
                        System.err.println("cycle detected: " + file);
                    } else {
                        System.err.format("Unable to copy: %s: %s%n", file, exc);
                    }
                    return CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    // fix up modification time of directory when done
                    if (exc == null) {
                        Path newdir = target.resolve(source.relativize(dir));
                        try {
                            FileTime time = Files.getLastModifiedTime(dir);
                            Files.setLastModifiedTime(newdir, time);
                        } catch (IOException x) {
                            System.err.format("Unable to copy all attributes to: %s: %s%n", newdir, x);
                        }
                    }
                    return CONTINUE;
                }
            });
        } catch(IOException e){
            e.printStackTrace();
        }


//        try {
//            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
//                @Override
//                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
//                    try {
//                        Path targetFile = targetDir.resolve(sourceDir.relativize(file));
//                        Files.copy(file, targetFile);
//                    } catch (IOException ex) {
//                        System.err.println(ex);
//                    }
//                    return FileVisitResult.CONTINUE;
//                }
//
//                @Override
//                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
//                    try {
//                        Path newDir = targetDir.resolve(sourceDir.relativize(dir));
//                        Files.createDirectory(newDir);
//                    } catch (IOException ex) {
//                        System.err.println(ex);
//                    }
//                    return FileVisitResult.CONTINUE;
//                }
//            });
//        } catch(IOException e){
//            e.printStackTrace();
//        }

    }

}
