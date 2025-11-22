const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split('\n');

const N = parseInt(input[0]);

for (let i = 0; i < N; i++) {
  const [a, b] = input[i + 1].split(' ');

  const arrA = a.split('').sort();
  const arrB = b.split('').sort();

  console.log(isPossible(arrA, arrB));
}

function isPossible(arrA, arrB) {
  for (let i = 0; i < arrA.length; i++) {
    if (arrA[i] !== arrB[i]) {
      return 'Impossible';
    }
  }
  return 'Possible';
}