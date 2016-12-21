<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

    <head>
        <title> Alchemy Vision result to speech </title>
    </head>
    <body>

        <h1>Face recognition</h1>
        <form method="post" action="/Alch/send">
            URL картинки:
            <input type="text" id="url" name="url" />
            <br/>
            <br/>
            <input type="submit" value="Recognize" />
            <br/>
        </form>

        <br/>
        <img id="im" src="${picture_url}" style="outline-color: red; z-index: 2;"/>
        <br/>
        <br/>
        <script type="application/javascript">
            <c:forEach items="${resp.imageFaces}" var="face">
                var element = document.createElement("div");
                element.style.position = "absolute";

                var l = ${face.positionX} + document.getElementById("im").offsetLeft;
                element.style.left = l + "px"

                element.style.width = "${face.width}px";

                var t = ${face.positionY} + document.getElementById("im").offsetTop;
                element.style.top = t + "px";

                element.style.height = "${face.height}px";

                element.style.outline = "thick solid #0000FF";
                element.style.zIndex = 3;
                document.body.appendChild(element);
            </c:forEach>
        </script>
        <br/>
        <form method="post" action="/Alch/classify">
            URL картинки:
            <input type="text" id="url" name="url" />
            <br/>
            <br/>
            <input type="submit" value="Classify" />
            <br/>
            <br/>
            <table border = "1px solid black">
                <caption>Classification</caption>
                <thead>
                    <tr>
                        <td>Class</td>
                        <td width="20%">Score</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${classification.images[0].classifiers[0].classes}" var="class">
                        <tr>
                            <td>${class.classname}</td>
                            <td>${class.score}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


        </form>
        <br/>


        <h1>Text-to-speech</h1>

        <form method="get" action="/Alch/speechText" >
            Выберите голос для воспроизведения:
            <br/>
            <select name="voice" id="voice">
                <option>Lisa</option>
                <option>Michael</option>
                <option>Allison</option>
            </select>
            <br/>
            <br/>
            <input type="submit" value="Play" />
        </form>

    </body>
</html>