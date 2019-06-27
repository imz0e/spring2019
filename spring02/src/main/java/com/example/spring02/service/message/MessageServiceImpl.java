package com.example.spring02.service.message;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.message.dao.MessageDAO;
import com.example.spring02.model.message.dao.PointDAO;
import com.example.spring02.model.message.dto.MessageDTO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject MessageDAO messageDAO;
	@Inject PointDAO pointDAO;
	
	@Transactional	// method 내부의 코드를 트랜잭션으로 묶음
	@Override
	public void addMessage(MessageDTO dto) {
		// 로그 확인(공통업무)
		// 핵심업무
		// 메시지를 테이블에 저장
		messageDAO.create(dto);
		// 메시지를 발송한 회원에게 10포인트 추가
		pointDAO.updatePoint(dto.getSender(), 10);
		
	}

	@Override
	public MessageDTO readMessage(String userid, int mid) {
		// TODO Auto-generated method stub
		return null;
	}

}
