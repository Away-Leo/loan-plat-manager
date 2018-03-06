package com.pingxun.biz.loandemand.app.service;

import com.pingxun.biz.common.dto.Pages;
import com.pingxun.biz.loandemand.app.dto.LoanBorrowRelationDto;
import com.pingxun.biz.loandemand.app.dto.LoanDemandDto;
import com.pingxun.biz.loandemand.domain.service.LoanBorrowRelationDomainService;
import com.pingxun.biz.message.app.dto.MessageDto;
import com.pingxun.biz.message.app.service.MessageAppService;
import com.pingxun.biz.user.app.service.CwUserInfoAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 借款放款关系需求服务
 * Created by dujy on 2017-05-20.
 */
@Transactional
@Service
public class LoanBorrowRelationAppService {

    @Autowired
    private LoanBorrowRelationDomainService loanBorrowRelationDomainService;

    /**
     * 新增借款需求
     * @param loanBorrowRelationDto
     * @return
     */
    public Long create(LoanBorrowRelationDto loanBorrowRelationDto){
        Long id = loanBorrowRelationDomainService.create(loanBorrowRelationDto).getId();
        return id;
    }

    /**
     * 修改借款需求
     * @param loanDemandDto
     * @return
     */
    public LoanDemandDto update(LoanBorrowRelationDto loanDemandDto){
        return loanBorrowRelationDomainService.update(loanDemandDto).to(LoanDemandDto.class);
    }

    /**
     * 查询借款详情
     * @param id
     * @return
     */
    public LoanBorrowRelationDto findById(Long id){
        return loanBorrowRelationDomainService.findById(id).to(LoanBorrowRelationDto.class);
    }

    /**
     * 查询需求id
     * @param loanBorrowRelationDto
     * @return
     */
    public LoanBorrowRelationDto findByUserId(LoanBorrowRelationDto loanBorrowRelationDto){
        return loanBorrowRelationDomainService.findLoanId(loanBorrowRelationDto).to(LoanBorrowRelationDto.class);
    }

    /**
     * 查询借款放款列表
     * @return
     */
    public Page<LoanBorrowRelationDto> findByCondition(LoanBorrowRelationDto dto){
        return Pages.map(loanBorrowRelationDomainService.findByCondition(dto),LoanBorrowRelationDto.class);
    }
}
