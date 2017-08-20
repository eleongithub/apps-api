package com.syscom.apps.web.service.v1.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.attachment.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.syscom.apps.business.service.FileService;
import com.syscom.apps.dto.AdvertDTO;
import com.syscom.apps.dto.CustomerDTO;
import com.syscom.apps.web.service.BaseEndPoint;

@Component("attachmentEndPoint")
@Path("/")
public class AttachmentEndPoint  extends BaseEndPoint{
	
	@Autowired
	private FileService fileService;
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uploadFile")
	public Response uploadFile(@Multipart(value = "file") Attachment attachment) throws IOException{ //type="application/octet-stream"
//		@Multipart( value = "docModel",type="application/json") DocumentApiModel doc
		fileService.createFileResource(attachment.getDataHandler().getName(), attachment.getDataHandler());
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uploadFile1")
	public Response uploadFile1(List<Attachment> attachments) throws IOException{ //type="application/octet-stream"
		for (Attachment att : attachments) {
		    System.out.println("attachment content type: {}"+att.getContentDisposition().getParameter("name"));
		}
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( MediaType.APPLICATION_JSON )
	@Path("/uploadFile2")
	public Response uploadFile2(@Multipart(value="file") Attachment attachment,
								@Multipart(value="customerDTO", type=MediaType.APPLICATION_JSON)CustomerDTO customerDTO) throws IOException{ //type="application/octet-stream"
		System.out.println("attachment content type attachment: {}"+attachment.getContentType().toString());
		System.out.println("attachment content type customerDTO: {}"+customerDTO.toString());
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
	
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces( MediaType.APPLICATION_JSON )
	@Path("/uploadFile3")
	public Response addAttachments(MultipartBody body) throws Exception {
		Attachment attachmentFile = null;
		CustomerDTO customerDTO = null;
		AdvertDTO advertDTO = null;
//		CustomerDTO customerDTO = body.getAttachmentObject("customerDTO", CustomerDTO.class);
		
		System.out.println("Size :"+body.getAllAttachments().size());
		for(Attachment attachment : body.getAllAttachments()){
			System.out.println("content ID : "+attachment.getContentId());
			ContentDisposition contentDisposition = attachment.getContentDisposition();
			System.out.println("content name : "+contentDisposition.getParameter("name"));
			if("advertDTO".equals(contentDisposition.getParameter("name"))){
				advertDTO = attachment.getObject(AdvertDTO.class);
			}
			
			if("customerDTO".equals(contentDisposition.getParameter("name"))){
				customerDTO = attachment.getObject(CustomerDTO.class);
			}
			
			if("file".equals(contentDisposition.getParameter("name"))){
				attachmentFile = attachment;
			}
			
		}
		
		System.out.println(body.getAllAttachments());
		System.out.println(body.getRootAttachment());
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
	
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces( MediaType.APPLICATION_JSON )
//	@Path("/uploadFile4")
//	public Response addAttachments4(MultipartBody body) throws Exception {
//		CustomerDTO customerDTO = body.getAttachmentObject("customerDTO", CustomerDTO.class);
//		Attachment attachment = body.getAttachment("file");
//		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).build();
//	}
	
}
