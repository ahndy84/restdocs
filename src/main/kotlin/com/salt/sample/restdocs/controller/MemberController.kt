package com.salt.sample.restdocs.controller

import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.ApiResponse
import com.salt.sample.restdocs.dto.member.request.MemberBody
import com.salt.sample.restdocs.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/member")
class MemberController(
        private val memberService: MemberService
) {

    @PostMapping
    fun createMember(@Valid @RequestBody memberBody: MemberBody): ResponseEntity<ApiResponse<Member>> {
        val response = ApiResponse.success(memberService.create(memberBody))
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.data?.id)
                .toUri()

        return ResponseEntity.created(location).body(response)
    }

    @GetMapping("/{memberId}")
    fun retrievalMember(@PathVariable memberId: Long): ResponseEntity<ApiResponse<List<Member>>> {
        val response = ApiResponse.success(memberService.retrieval(memberId))
        return ResponseEntity.ok().body(response)
    }

    @PutMapping("/{memberId}")
    fun updateMember(
            @PathVariable memberId: Long,
            @RequestBody memberBody: MemberBody): ResponseEntity<ApiResponse<Long>> {
        val response = ApiResponse.success(memberService.update(memberBody))
        return ResponseEntity.ok().body(response)
    }

    @DeleteMapping("/{memberId}")
    fun deleteMember(@PathVariable memberId: Long): ResponseEntity<ApiResponse<Long>> {
        val response = ApiResponse.success(memberService.delete(memberId))
        return ResponseEntity.ok().body(response)
    }
}