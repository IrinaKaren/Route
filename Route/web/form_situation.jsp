<%@page import="java.util.List"%>
<%@page import="model.Situation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Situation> listpk = (List<Situation>)request.getAttribute("listpk");
    List<Situation> degats = Situation.getAllDegat();
    String argent = (String)request.getAttribute("argent");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire situation routière</title>
    <style>
        body {
            font-family: 'Cursive', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 70px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 10.15);
            text-align: center; 
        }

        input, select, a, .add-button {
            width: calc(48% - 10px);
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            display: inline-block;
        }

        input[type="submit"], a, .add-button {
            font-family: 'Cursive', sans-serif;
            font-size: 18px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
        }

        .add-button {
            background-color: #dc3545;
            border-radius: 4px;
        }

        .add-button:hover {
            background-color: #bd2130;
        }

        .added-inputs {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .added-inputs input, .added-inputs select {
            width: calc(59% - 21px);
        }
    </style>
</head>

<script>
    function addInputFields() {
        var container = document.getElementById('added-inputs-container');
        var newInputs = container.cloneNode(true);

        var inputs = newInputs.getElementsByTagName('input');
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].value = '';
        }

        container.parentNode.insertBefore(newInputs, container.nextSibling);
    }
</script>


<body>
    <form action="FormSituationController" method="post">
        <p>Formulaire situation routière</p>
        <div id="added-inputs-container" class="added-inputs">
            <input type="hidden" value="<%=argent%>" name="argent">
            <select id="type" name="idpk">
                <% for(int i = 0; i<listpk.size(); i++){ %>
                    <option value="<%=listpk.get(i).getIdpk()%>"><%=listpk.get(i).getPk()%></option>
                <% } %>
            </select>
            <select id="type" name="id_degat">
                <% for(int i = 0; i<degats.size(); i++){ %>
                    <option value="<%=degats.get(i).getId_degat()%>"><%=degats.get(i).getDegat()%></option>
                <% } %>
            </select>
        </div>
        <button type="button" class="add-button" onclick="addInputFields()">+</button>
        <input type="submit" value="Valider">
    </form>
</body>
</html>