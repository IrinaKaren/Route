<%@page import="java.util.List"%>
<%@page import="model.Route"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Route> routes = Route.getAll();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des routes</title>
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
            width: calc(70% - 10px);
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
            width: calc(110% - 10px);
        }
    </style>
</head>

<body>
    <form action="RouteController" method="post">
        <p>Choissisez une route</p>
        <div id="added-inputs-container" class="added-inputs">
            <select id="type" name="id_route">
                <% for(int i = 0; i<routes.size(); i++) { %>
                    <option value="<%=routes.get(i).getId()%>"><%=routes.get(i).getNom()%></option>
                <% } %>
            </select>
        </div>
        <input type="submit" value="Valider">
    </form>
</body>
</html>