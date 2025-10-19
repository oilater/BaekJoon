const fs = require('fs');
const inputs = fs.readFileSync(0).toString().trim().split('\n');

const res = Array(10).fill(0);
let sum = 1;
for (const num of inputs) {
    sum *= num;
}

for (const num of sum.toString().split('')) {
    res[num]++;
}

for (const num of res) {
    console.log(num);
}