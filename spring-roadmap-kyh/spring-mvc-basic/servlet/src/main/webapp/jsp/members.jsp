<%@ page import="springmvc.servlet.domain.member.MemberRepository" %>
<%@ page import="springmvc.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 회원 저장소
  MemberRepository memberRepository = MemberRepository.getInstance();
  // 회원 저장소에 저장된 모든 회원 정보 반환
  List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <a href="/index.html">메인</a>
  <table>
    <thead>
      <th>id</th>
      <th>username</th>
      <th>age</th>
    </thead>
    <tbody>
      <%
        for (Member member : members) {
          out.write(" <tr>");
          out.write("   <td>" + member.getId() + "</td>");
          out.write("   <td>" + member.getUsername() + "</td>");
          out.write("   <td>" + member.getAge() + "</td>");
          out.write(" </tr>");
        }
      %>
    </tbody>
  </table>
</body>
</html>
