package com.joeldholmes.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.joeldholmes.dto.TaoVerseDTO;
import com.joeldholmes.exceptions.ServiceException;
import com.joeldholmes.services.interfaces.ITaoService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

@Component
@Scope("prototype")
public class GetTaoVersesByStringCommand extends BaseCommand<List<TaoVerseDTO>> {
	
	@Autowired
	ITaoService taoService;
	
	private String verses;
	
	protected GetTaoVersesByStringCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SacredTextAPI"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetTaoVerseString")));
	}
	
	public GetTaoVersesByStringCommand setVerses(String verses){
		this.verses=verses;
		return this;
	}
	
		
	@Override
	protected List<TaoVerseDTO> run() throws Exception {
		try{
			return taoService.getVersesFromString(verses);
		}
		catch(ServiceException e){
			log.error("error creating getting verse "+e);
			throw new HystrixBadRequestException("unable to process request", e);
		}
	}
	
	

}

