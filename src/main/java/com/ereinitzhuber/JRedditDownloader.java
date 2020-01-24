package com.ereinitzhuber;

import com.ereinitzhuber.DownloadThreads.*;
import com.ereinitzhuber.Exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main entry point. This class contains all of the static methods needed.
 * For all of the download methods URLs that fail to retried twice and then
 * silently failed. DownloadImages and PushshiftGetLinks store the API
 * response in a .JSON file to keep a reference of all downloaded files.
 * This file contains URLs, original post links, titles, and timestamps.
 *
 * This program is a work in progress with hopefully many more download methods
 * and optimizations in it's future.
 */
public class JRedditDownloader {
    private static final int DEFAULT_MAX_THREADS = 20;
    /**
     * Method to get a list of all media URLs from a specific
     * subreddit for the end user to use with their preferred downloading method.
     *
     * @param subreddit Subreddit to pull links from.
     * @param path Full path to save .JSON file with links, titles,
     * original post URLs, and timestamps.
     * @return List of Strings representing all of the URLs returned
     * from Pushshift.
     * @throws PushshiftConnectionException When a connection cannot be made
     * to the Pushshift API service.
     */
    public static List<String> PushshiftLinksOnly(String subreddit, String path) throws PushshiftConnectionException {
        return Pushshift.getLinks(subreddit, path);
    }
    /**
     * Method to download a user supplied list of Imgur images. Supports all
     * Imgur URL types and downloads all images in an album to a new
     * folder. Non Imgur URLs won't pass regex check.
     *
     * @param URLS List of Imgur URLs
     * @param path Save directory
     */
    public static void ManualImgurDownload(List<String> URLS, String path) {
        ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_MAX_THREADS);
        List<DLThread> threadPool = new ArrayList<>();
        for (String url : URLS) {
            threadPool.add(new ImgurDownloadThread(url, path));
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * Overloaded ManualImgurDownload method supporting a user defined
     * thread pool size for downloading images. Default is 20.
     *
     * @param URLS List of Imgur URLs
     * @param path Save directory
     * @param maxThreads Maximum number of threads
     */
    public static void ManualImgurDownload(List<String> URLS, String path, int maxThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        List<DLThread> threadPool = new ArrayList<>();
        for (String url : URLS) {
            threadPool.add(new RedditDownloadThread(url, path));
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * Method to download a user supplied list of i.redd.it images.
     * Upon passing an empty list method silently fails.
     *
     * @param URLS List of Reddit URLs
     * @param path Save directory
     */
    public static void ManualRedditDownload(List<String> URLS, String path) {
        ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_MAX_THREADS);
        List<DLThread> threadPool = new ArrayList<>();
        for (String url : URLS) {
            threadPool.add(new RedditDownloadThread(url, path));
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * Overloaded ManualRedditDownload method supporting a user defined
     * thread pool size for downloading images. Default is 20.
     *
     * @param URLS List of Reddit URLs
     * @param path Save directory
     * @param maxThreads Maximum number of threads
     */
    public static void ManualRedditDownload(List<String> URLS, String path, int maxThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        List<DLThread> threadPool = new ArrayList<>();
        for (String url : URLS) {
            threadPool.add(new RedditDownloadThread(url, path));
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * Method to download a user supplied list of Twitter images.
     * Page source of the tweet is parsed to extract direct image URL.
     * Upon passing an empty list method silently fails.
     *
     * @param URLS List of Twitter URLs
     * @param path Save directory
     */
    public static void ManualTwitterDownload(List<String> URLS, String path) {
        ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_MAX_THREADS);
        List<DLThread> threadPool = new ArrayList<>();
        for (String url : URLS) {
            threadPool.add(new TwitterDownloadThread(url, path));
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * Overloaded ManualTwitterDownload method supporting a user defined
     * thread pool size for downloading images. Default is 20.
     *
     * @param URLS List of Twitter URLs
     * @param path Save directory
     * @param maxThreads Maximum number of threads
     */
    public static void ManualTwitterDownload(List<String> URLS, String path, int maxThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        List<DLThread> threadPool = new ArrayList<>();
        for (String url : URLS) {
            threadPool.add(new TwitterDownloadThread(url, path));
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * Method to download a user supplied list of misc images.
     * Image URLs that have a file extension are supported, those
     * that don't are not.
     *
     * @param URLS List of URLs
     * @param path Save directory
     */
    public static void ManualImageDownload(List<String> URLS, String path) {
        ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_MAX_THREADS);
        List<DLThread> threadPool = new ArrayList<>();
        for (String url : URLS) {
            threadPool.add(new MiscDownloadThread(url, path));
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * Overloaded ManualImageDownload method supporting a user defined
     * thread pool size for downloading images. Default is 20.
     *
     * @param URLS List of URLs
     * @param path Save directory
     * @param maxThreads Maximum number of threads
     */
    public static void ManualImageDownload(List<String> URLS, String path, int maxThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        List<DLThread> threadPool = new ArrayList<>();
        for (String url : URLS) {
            threadPool.add(new MiscDownloadThread(url, path));
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * This method combines all of the previous methods allowing one method call
     * to execute the entire sequence of getting URLs, sorting, and downloading.
     * Imgur, i.redd.it, Twitter, and any direct image links are sorted and downloaded
     * through their respective download threads.
     *
     * @param subreddit Subreddit to download images from.
     * @param path Save directory.
     * @throws JRedditDownloadException Upon Pushshift connection error.
     */
    public static void downloadImages(String subreddit, String path) throws JRedditDownloadException {
        ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_MAX_THREADS);
        ArrayList<String> URLS;
        List<DLThread> threadPool = new ArrayList<>();
        try {
            URLS = (ArrayList<String>) Pushshift.getLinks(subreddit, path);
        }
        catch (PushshiftConnectionException e) {
            throw new JRedditDownloadException("Pushshift connection failed.", e);
        }
        for (String url : URLS) {
            if (url.contains("imgur.com")) {
                threadPool.add(new ImgurDownloadThread(url, path));
            }
            else if (url.contains("i.redd.it")) {
                threadPool.add(new RedditDownloadThread(url, path));
            }
            else if (url.contains("twitter.com")) {
                threadPool.add(new TwitterDownloadThread(url, path));
            }
            else if (url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif")) {
                threadPool.add(new MiscDownloadThread(url, path));
            }
            //TODO other download opts if implemented
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
    /**
     * Overloaded downloadImages method supporting a user defined
     * thread pool size for downloading images. Default is 20.
     *
     * @param subreddit Subreddit to download images from.
     * @param path Save directory.
     * @param maxThreads Maximum number of threads.
     * @throws JRedditDownloadException Upon Pushshift connection error.
     */
    public static void downloadImages(String subreddit, String path, int maxThreads) throws JRedditDownloadException {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        ArrayList<String> URLS;
        List<DLThread> threadPool = new ArrayList<>();
        try {
            URLS = (ArrayList<String>) Pushshift.getLinks(subreddit, path);
        }
        catch (PushshiftConnectionException e) {
            throw new JRedditDownloadException("Pushshift connection failed.", e);
        }
        for (String url : URLS) {
            if (url.contains("imgur.com")) {
                threadPool.add(new ImgurDownloadThread(url, path));
            }
            else if (url.contains("i.redd.it")) {
                threadPool.add(new RedditDownloadThread(url, path));
            }
            else if (url.contains("twitter.com")) {
                threadPool.add(new TwitterDownloadThread(url, path));
            }
            else if (url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif")) {
                threadPool.add(new MiscDownloadThread(url, path));
            }
        }
        for (DLThread thread : threadPool) {
            executorService.submit(thread);
        }
        executorService.shutdown();
    }
}