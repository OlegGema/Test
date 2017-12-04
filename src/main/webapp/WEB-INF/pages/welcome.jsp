<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>textfileAPI</title>

</head>

<div style="border: solid orange 2px;width: 300px;margin: 30px auto;padding: 20px;text-align: center">
    <form action="textfileAPI" method="get">

        Search:
        <input type="text" name="q" value="" title="" style="margin-bottom: 10px;">
        <br>

        Chars Limit:
        <input type="number" name="limit" min="0" title="" style="margin-bottom: 10px">
        <br>
        String Length:
        <input type="number" name="length" min="0" title="" style="margin-bottom: 10px">
        <br>
        Include Meta Data?
        <select name="includeMetaData" title="">
            <option value="false">No</option>
            <option value="true">Yes</option>
        </select>
        <br>
            <input  type="submit" value="Serach" >
    </form>
</div>
</body>
</html>