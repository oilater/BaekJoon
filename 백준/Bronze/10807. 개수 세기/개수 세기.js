const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split('\n');
const arr = input[1].split(' ').map(Number).sort((a, b) => a - b);
const v = Number(input[2]);

let res = 0;
for (const a of arr) {
    if (v === a) res++;
}

console.log(res);