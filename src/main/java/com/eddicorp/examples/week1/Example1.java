package com.eddicorp.examples.week1;

public class Example1 {

    public static void main(String[] args) {
        final String request =
                "POST /api/signup HTTP/1.1\r\n" +
                        "Content-Type: application/json; charset=utf8\r\n" +
                        "Content-Length: 232\r\n" +
                        "\r\n" +
                        "{'email': 'dkanakf@dkanrjsk', 'password': 'dhodlrjfgotjrgkrhdlTdjdy?', 'allowMarketing': 'on'}";

        /**
         * 실습 - 요청 파싱
         * 1. 요청라인, 헤더, 바디를 나누어보자
         * 2. 요청라인을 나누어 다음과 같이 출력한다
         *  2.1. method: POST
         *  2.2. uri: /api/signup
         *  2.3. protocol: HTTP/1.1
         * 3. 모든 헤더를 다음과 같은 포맷으로 출력한다.
         *  3.1. header name: Content-Type, header value: application/json; charset=utf8
         * 4. 바디를 출력한다.
         *  4.1. body: {'email': 'dkanakf@dkanrjsk', 'password': 'dhodlrjfgotjrgkrhdlTdjdy?', 'allowMarketing': 'on'}
         */
        final String[] lines = request.split("\r\n");
        final String requestLine = lines[0];
        final String[] partsOfRequestLine = requestLine.split(" ");
        System.out.println("method: " + partsOfRequestLine[0]);
        System.out.println("uri: " + partsOfRequestLine[1]);
        System.out.println("protocol: " + partsOfRequestLine[2]);
        final int length = lines.length;
        for (int i = 1; i < length; i++) {
            final String headerNameAndHeaderValue = lines[i];
            if ("".equals(headerNameAndHeaderValue)) {
                break;
            }
            final String[] nameAndValue = headerNameAndHeaderValue.split(":");
            System.out.println("header name: " + nameAndValue[0].trim() + ", header value: " + nameAndValue[1].trim());
        }
        final String body = lines[length - 1];
        System.out.println("body: " + body);
    }
}
