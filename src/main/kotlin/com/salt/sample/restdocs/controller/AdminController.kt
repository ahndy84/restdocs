package com.salt.sample.restdocs.controller

import com.salt.sample.restdocs.domain.member.Member
import com.salt.sample.restdocs.dto.ApiResponse
import com.salt.sample.restdocs.dto.member.request.MemberBody
import com.salt.sample.restdocs.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/admin")
class AdminController(
        private val memberService: MemberService
) {

    @PostMapping
    fun createMember(@RequestBody adminBody: MemberBody): ResponseEntity<ApiResponse<Member>> {
        val response = ApiResponse.success(memberService.create(adminBody))
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.data?.id)
                .toUri()

        return ResponseEntity.created(location).body(response)
    }

    @GetMapping("/{adminId}")
    fun retrievalMember(@PathVariable adminId: Long): ResponseEntity<ApiResponse<List<Member>>> {
        val response = ApiResponse.success(memberService.retrieval(adminId))
        return ResponseEntity.ok().body(response)
    }

    @PutMapping("/{adminId}")
    fun updateMember(
            @PathVariable adminId: Long,
            @RequestBody adminBody: MemberBody): ResponseEntity<ApiResponse<Long>> {
        val response = ApiResponse.success(memberService.update(adminBody))
        return ResponseEntity.ok().body(response)
    }

    @DeleteMapping("/{adminId}")
    fun deleteMember(@PathVariable adminId: Long): ResponseEntity<ApiResponse<Long>> {
        val response = ApiResponse.success(memberService.delete(adminId))
        return ResponseEntity.ok().body(response)
    }
}
