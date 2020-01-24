package DownloadThreads;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class RedditDownloadThread  implements DLThread {
    String baseUrl;
    String path;
    String filename;
    File outputfile;
    Thread me;

    public RedditDownloadThread(String url, String path) {
        me = new Thread(this);
        baseUrl = url;
        this.path = path;
        int startIndex = baseUrl.lastIndexOf("/");
        String name = baseUrl.substring(startIndex);
        filename = path + name;
        outputfile = new File(filename);
    }

    @Override
    public void run() {
        boolean fileDownloadedFlag = false;
        if (outputfile.exists()) { fileDownloadedFlag = true; }
        int retries = 0;

        while (!fileDownloadedFlag){
            try {
                download(baseUrl);
                fileDownloadedFlag = true;
            }
            catch (IOException e) {
                if (retries == 2) {
                    outputfile.delete();
                    break;
                }
                retries++;
            }
        }
    }

    @Override
    public void download(String myUrl) throws IOException {
        FileChannel fileChannel = null;
        ReadableByteChannel readableByteChannel = null;
        FileOutputStream fileOutputStream = null;

        try {
            URL urlObj = new URL(myUrl);
            readableByteChannel =
                    Channels.newChannel(urlObj.openStream());
            fileOutputStream = new FileOutputStream(outputfile);
            fileChannel = fileOutputStream.getChannel();
            fileOutputStream.getChannel().transferFrom(
                    readableByteChannel, 0, Long.MAX_VALUE);
        }
        finally {
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (readableByteChannel != null) {
                readableByteChannel.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
}