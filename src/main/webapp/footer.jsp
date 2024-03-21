<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Go to Home Page</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 0;
    }

    .links {
      text-align: center;
      margin-top: 50px;
    }

    .links button {
      padding: 10px 20px;
      font-size: 16px;
      background-color: #ffa5f8;
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    .links button:hover {
      background-color: #cababf;
    }
  </style>
</head>
<body>
<div class="links">
  <button onclick="location.href='index.html'">Go to Home Page</button>
</div>
</body>
</html>
