const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split('\n');

const N = parseInt(input[0]);
const arr = input[1].split(' ').map(Number);
const operators = input[2].split(' ').map(Number);

let min = 1_000_000_001;
let max = -1_000_000_001;

function calculate(a, operatorIndex, b) {
    if (operatorIndex === 0) {
        return a + b;
    } else if (operatorIndex === 1) {
        return a - b;
    } else if (operatorIndex === 2) {
        return a * b;
    } else if (operatorIndex === 3) {
        if (a >= 0) {
            return ~~(a / b);
        } else {
            return ~~-(-a / b);
        }
    }
}

function dfs(depth, value) {
    if (depth === N - 1 ) {
        max = Math.max(value, max);
        min = Math.min(value, min);
        return;
    }

    for (let i =0; i < 4; i++) {
        if (operators[i] <= 0) continue;
        operators[i]--;
        dfs(depth + 1, calculate(value, i, arr[depth + 1]));
        operators[i]++;
    }
}

dfs(0, arr[0]);
console.log(max);
console.log(min);
