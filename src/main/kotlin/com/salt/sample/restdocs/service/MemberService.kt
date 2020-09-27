package com.salt.sample.restdocs.service

import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.domain.member.MemberRepository
import com.salt.sample.restdocs.dto.member.request.MemberBody
import org.springframework.stereotype.Service

@Service
class MemberService(
        private val memberRepository: MemberRepository
) {
    fun create(memberBody: MemberBody): Member {
        return memberRepository.save(Member(memberBody))
    }
    fun update(memberBody: MemberBody): Long = 0

    fun retrieval(MemberId: Long): List<Member> {
        return memberRepository.findAll()
    }

    fun delete(MemberId: Long): Long {
        memberRepository.deleteById(MemberId)
        return MemberId
    }
}
