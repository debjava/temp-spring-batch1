-- To see the job execution details
select bji.job_instance_id, bji.job_name, bje.start_time, bje.end_time, bje.status, bje.exit_code, bje.exit_message, bje.last_updated 
  from BATCH_JOB_INSTANCE bji, BATCH_JOB_EXECUTION bje 
    where bji.job_instance_id = bje.job_instance_id;
    
-- To see the step execution details
select bje.job_execution_id, bse.step_name, bse.start_time, bse.end_time, bse.status, bse.exit_code
   from BATCH_JOB_EXECUTION bje, BATCH_STEP_EXECUTION bse
   where bje.job_execution_id = bse.job_execution_id;