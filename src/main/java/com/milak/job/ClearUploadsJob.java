package com.milak.job;

import com.milak.service.PostService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ClearUploadsJob extends QuartzJobBean {

    @Autowired
    private PostService postService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        postService.clearAllUploads();
    }
}
