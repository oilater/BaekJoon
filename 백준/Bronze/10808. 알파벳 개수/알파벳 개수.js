const fs = require('fs');
const input = fs.readFileSync("/dev/stdin").toString().trim().split('');

const res = Array(26).fill(0);

for (const c of input) {
    ++res[c.charCodeAt(0) - 'a'.charCodeAt(0)];
}
console.log(...res);