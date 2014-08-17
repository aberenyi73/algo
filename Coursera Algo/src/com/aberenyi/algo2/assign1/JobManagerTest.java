package com.aberenyi.algo2.assign1;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class JobManagerTest {
    
    @Test
    public void testRead2() {
        List<Job> jobList = JobManager
                .readJobs("/Users/aberenyi/Documents/workspace/Coursera Algo 2/input/algo2/assign1/jobs.txt", true);
        Assert.assertEquals(10000,jobList.size());
    }

    @Test
    public void testWSS() {
        List<Job> jobLIst = JobManager
                .readJobs("/Users/aberenyi/Documents/workspace/Coursera Algo 2/input/algo2/assign1/jobs.txt", false);

        int a = JobManager.sumOfWeighedCompletionTimes(jobLIst);
        System.out.println("WSS = " + a);
    }

    @Test
    public void testWSS2() {
        List<Job> jobList = JobManager
                .readJobs("/Users/aberenyi/Documents/workspace/Coursera Algo 2/input/algo2/assign1/jobs.txt", true);

        int a = JobManager.sumOfWeighedCompletionTimes(jobList);
        System.out.println("WSS2 = " + a);
    }

    

}
