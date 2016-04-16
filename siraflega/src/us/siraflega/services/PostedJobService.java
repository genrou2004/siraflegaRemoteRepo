package us.siraflega.services;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import us.siraflega.entities.Employer;
import us.siraflega.entities.PostedJob;
import us.siraflega.repositories.JobRepository;

@Service
public class PostedJobService {
 @Autowired
 JobRepository jobRepository;
	public List<PostedJob> getPostedJobs(Employer employer,int pageNumber, int pageHoldingCapacity) {
//		new PageRequest(0, 10, Direction.DESC, "publishedDate")
//		, new PageRequest(pageNumber, 10, Direction.DESC, "deadLine")
		return jobRepository.findByJobPostedBy(employer, new PageRequest(pageNumber-1, pageHoldingCapacity, Direction.DESC, "deadLine"));
	}
	public List<PostedJob> getPostedJobs(Employer employer) {
		// TODO Auto-generated method stub
		return jobRepository.findByJobPostedBy(employer);
	}
	public void save(PostedJob job) {
		System.out.println("the id to be saveeeeeeeeeeeeeeeeeeeeeeeeeed"+job.getId());
		jobRepository.save(job);
	}
	public PostedJob getPostdJob(Integer id) {
		// TODO Auto-generated method stub
		return jobRepository.findOne(id);
	}
	public void deletePostedJob(int id) {
		jobRepository.delete(id);
		
	}
	public int getPostedJobsSize() {
		// TODO Auto-generated method stub
		return (int) jobRepository.count();
	}
	public List<PostedJob> getPostedJobs(int pageNumber, int pageHoldingCapacity) {
		// TODO Auto-generated method stub
		Page<PostedJob> currentPage=jobRepository.findAll(new PageRequest(pageNumber-1, pageHoldingCapacity, Direction.DESC, "deadLine"));
		List<PostedJob> jobList=new ArrayList<PostedJob>();
		for(PostedJob postedJob:currentPage){
			jobList.add(postedJob);
		}
		return jobList;
	}

}
