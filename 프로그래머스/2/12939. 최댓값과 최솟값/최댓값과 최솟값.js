function solution(s) {
    var answer = '';
    
    const arr = s.split(' ').map(Number);
    
    return `${Math.min(...arr) + ' ' + Math.max(...arr)}`;
}