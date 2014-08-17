package com.aberenyi.algo2.assign1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobManager {

    public static List<Job> readJobs(String fileName, boolean isDivide) {
        List<Job> answer = new ArrayList<Job>();
        BufferedReader br = null;
        try {
            FileReader fi = new FileReader(fileName);
            br = new BufferedReader(fi);
            String line = null;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                    continue;
                }
                String[] elements = line.split(" ");
                int weight = Integer.parseInt(elements[0]);
                int length = Integer.parseInt(elements[1]);
                Job job = new Job(weight, length);
                job.setDivide(isDivide);
                answer.add(job);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(answer);
        return answer;
    }

    /**
     * Calculate the sum of weighed completion times, that is
     * job1_weight*job1_length + job2_weight*(job1_length+job2_length) + ...
     * 
     * @param jobList
     * @return
     */
    public static int sumOfWeighedCompletionTimes(List<Job> jobList) {
        int answer = 0;
        int runningCompletionTime = 0;
        for (Job job : jobList) {
            runningCompletionTime += job.getLength();
            answer = job.getWeight() * runningCompletionTime;
        }
        return answer;
    }
}
