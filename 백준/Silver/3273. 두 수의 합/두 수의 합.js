const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split('\n');
const arr = input[1].split(' ').map(Number).sort((a, b) => a - b);
const x = Number(input[2]);

let start = 0;
let end = arr.length - 1;
let res = 0;
while (start < end) {
    const sum = arr[start] + arr[end];
    if (sum === x) {
        ++res;
        ++start;
        --end;
    } else if (sum > x) {
        --end;
    } else {
        ++start;
    }
}

console.log(res);