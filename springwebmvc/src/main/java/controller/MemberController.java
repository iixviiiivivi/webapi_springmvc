package controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.MemberDAO.MemberDao;
import exception.CustomException;
import exception.ResponseMessage;
import model.Member;

@RequestMapping("webapi/member")
@RestController
public class MemberController {

	@Autowired
	private MemberDao memberDao;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Member> members = memberDao.findAll();
		if(!members.isEmpty())
			return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
		
		ResponseMessage rm = new ResponseMessage(
					HttpStatus.NOT_FOUND.value(), 
					HttpStatus.NOT_FOUND.name(), 
					"member's data does not exist"
				);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getMember(@PathVariable Integer id) {
		Member member = memberDao.findOne(id);
		if(member != null)
			return new ResponseEntity<Member>(member, HttpStatus.OK);
		
		ResponseMessage rm = new ResponseMessage(
					HttpStatus.NOT_FOUND.value(), 
					HttpStatus.NOT_FOUND.name(), 
					String.format("Member ID %d does not exist", id)
				);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> postMember(@Valid @RequestBody Member m) {
		Member member = memberDao.save(m);
		
		if(member != null)
			return new ResponseEntity<Member>(member, HttpStatus.OK);
		
		ResponseMessage rm = new ResponseMessage(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name(),
					"save member failed"
				);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> putMember(@PathVariable Integer id, @Valid @RequestBody Member m){
		Member member = memberDao.update(id, m);
		
		if(member != null)
			return new ResponseEntity<Member>(member, HttpStatus.OK);
		
		ResponseMessage rm = new ResponseMessage(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), 
					HttpStatus.INTERNAL_SERVER_ERROR.name(), 
					String.format("Updating member ID %d fail", id)
				);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteMember(@PathVariable Integer id){
		boolean flag = memberDao.delete(id);
		if (flag) {
			ResponseMessage rm = new ResponseMessage(
						HttpStatus.OK.value(), 
						HttpStatus.OK.name(), 
						String.format("Member ID %d is deleted", id)
					);
			return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
		}
		
		ResponseMessage rm = new ResponseMessage(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.name(),
					String.format("delete Member ID %d fail", id)
				);
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("custom")
	public String CustomExceptionTest() {
		throw new CustomException("custom exception test");
	}
	
}
