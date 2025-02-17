<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="springmvc.servlet.domain.member.Member" %>
<%@ page import="springmvc.servlet.domain.member.MemberRepository" %>
<%
  // 회원 저장소
  MemberRepository memberRepository = MemberRepository.getInstance();
  // 요청으로부터 요청 파라미터 반환 >> request와 response는 사용할 수 있다.
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));
  // 회원 저장소에 회원 정보 저장
  Member member = new Member(username, age);
  memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  성공
  <ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
  </ul>
  <a href="/index.html">메인</a>
</body>
</html>
