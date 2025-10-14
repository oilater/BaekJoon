const fs = require('fs');
const input = fs.readFileSync("/dev/stdin").toString().trim().split('');

const alpha = 'abcdefghijklmnopqrstuvwxyz'.split('');
const res = Array(26).fill(0);

for (let i = 0; i < input.length; i++) {
    const c = input[i];
    for (let j = 0; j < alpha.length; j++) {
        if (c === alpha[j]) res[j]++;
    }
}

console.log(...res);
