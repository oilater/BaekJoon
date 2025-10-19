const fs = require('fs');
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('');

const arr = Array(10).fill(0);

for (const c of inputs) {
    if (c === '9' || c === '6') {
        arr[6] += 0.5;
    } else {
        arr[c] += 1;
    }
}

console.log(Math.ceil(Math.max(...arr)));

