package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.model.Items;
import com.example.demo.repository.ItemRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
public class ItemService {
	
	
	@Autowired
	private ItemRepository itemrepository; 

	@GetMapping(value="/getItem/{id}", produces="application/json")
	@ApiOperation(value = "View the information of the requested item", response = Iterable.class, consumes="application/json", produces="application/json")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved the item"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public Optional getItem(@PathVariable String id) {
		Optional<Items> itemDetails = itemrepository.findById(Long.valueOf(id));	
		if(!itemDetails.isPresent()) {
			throw new ItemNotFoundException();
		}
		return itemDetails;
	}
	
	
	
	
	@GetMapping(value="/getAllItems")
	@ApiOperation(value = "View the information about all item", response = Iterable.class, consumes="application/json", produces="application/json")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved the item"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public List<Items> getAllItem() {		
		List<Items> itemList= itemrepository.findAll();
		if(itemList.isEmpty()){
			throw new ItemNotFoundException();
		}		
		return itemList;
	}
	
	
	
	@PostMapping(value="/insertItem")
	@ApiOperation(value = "Insert the Item information.", response = Iterable.class, consumes="application/json", produces="application/json")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Inserted the item"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),	        
	})
	public ResponseEntity insertItem(@RequestBody Items item) {
		System.out.println(item.getItemDescription()+"   "+item.getItemName()+"   "+item.getItemPrice());
		try {
			itemrepository.save(item);
		} catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		ResponseEntity response = new ResponseEntity<>("Item inserted successfully", HttpStatus.OK); 
		return  response;
		
	}
	
	
	@PutMapping(value="/putItem/{id}")
	@ApiOperation(value = "Update the requested Item information.", response = Iterable.class, consumes="application/json", produces="application/json")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Updated the item"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public ResponseEntity updateItem(@RequestBody Items item) {
		itemrepository.save(item);		
		ResponseEntity response = new ResponseEntity<>("Item updated successfully", HttpStatus.OK); 
		return  response;
	}
	
	
	@DeleteMapping(value="/deleteItem/{id}")
	@ApiOperation(value = "Deleted the requested Item.", response = Iterable.class, consumes="application/json", produces="application/json")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Deleted the item"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public ResponseEntity deleteItem(@PathVariable String id) {
		itemrepository.deleteById(Long.parseLong(id));
		ResponseEntity response = new ResponseEntity<>("Item deleted successfully", HttpStatus.OK); 
		return  response;
	}
	
	
}
