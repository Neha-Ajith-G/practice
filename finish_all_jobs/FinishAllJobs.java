//Given an array job[], where each element represents the time required to complete a specific job. There are k identical assignees available to complete these jobs, and each assignee takes t units of time to complete one unit of a job. The task is to determine the minimum time required to complete all jobs while following these constraints: 

//Each assignee can only be assigned jobs that are contiguous in the given array. For example, an assignee can be assigned jobs (job[1], job[2], job[3]) but not (job[1], job[3]) (skipping job[2]).
//A single job cannot be divided between two assignees. Each job must be assigned to exactly one assignee.

import java.util.*;

class FinishAllJobs {
    // checkk if jobs can be assigned such that each assignee only gets t units of job not more than that

    static boolean isPossible (int[] job, int t, int k){
        int assignee = 1;
        int currentLoad = 0;
        int i=0;
        while( i< job.length){
            //check if curr work  doesn't fit within the assignees load
            if( currentLoad + job[i]>t){
                assignee++;// new assignee is set
                currentLoad = 0;
            }
            else{
                currentLoad+= job[i];
                i++;//move to next job
            }
        }
        //returns true we use atmost k assignees
        return assignee <= k;
    }

    static int minFeasibleTime(int[] job,int t, int k){
        int low = 0;
        int high = 0;

        for(int j: job){
            high+= j;
            low = Math.max(low,j);

        }

        int answer = high;

        while (low<=high){
            int mid = (low+high)/2;

            if (isPossible(job, mid, k)){
                answer = mid;
                high = mid -1;
            }
            else {
                low = mid +1;
            }
        }

        answer = answer * t;
        return answer;
    }

    public static void main(String[] args) {
        int job[] = {10, 7, 8, 12, 6, 8};  // Job durations
        int k = 4;  // Number of assignees
        int t = 5;  // Time per job unit

        System.out.println(minFeasibleTime(job, k, t));
    }

}

