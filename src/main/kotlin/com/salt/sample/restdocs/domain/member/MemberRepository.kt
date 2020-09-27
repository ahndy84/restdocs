package com.salt.sample.restdocs.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>
