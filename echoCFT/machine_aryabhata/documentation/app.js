Math result: const http = require('request');
const express = require('express');
const bodyParser = require('body-parser');
var mathjs = require('mathjs')

const flag = require('./flag.json');
const fs = require("fs");
const PORT = process.env.NODE_PORT || 8001;
const HOST = process.env.NODE_HOST || '127.0.0.1';
const app = express();
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.post('/', (req, res) => {
    fs.readFile("app.js","utf8" ,function(err, contents){
      res.writeHead(200, {'Content-Type': 'text/plain'});
      res.write(contents);
      res.end();
    });
});

app.post('/maths', (req, res) => {
  res.writeHead(200, {'Content-Type': 'text/plain'});
  if (req.body.equation) {
    try {
      res.write("Math result: ")
      retval=mathjs.eval(req.body.equation);
      res.write(`${retval}`)
    }
    catch (e) {
      res.write("OUCH");
    }
    finally {
      res.write("");
    }
  } else {
    res.write('Need equation');
  }
  res.end();
});

app.listen(PORT,HOST, () => {
  console.log(`Example app listening on port ${HOST}:${PORT}!`);
});
// ETSCTF_1116e135b847722f3d127e9f9454e4a5
