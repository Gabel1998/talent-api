let dismissed = false;
const banner = document.getElementById('exit-banner');
const closeBtn = document.getElementById('exit-banner-close');

document.addEventListener('mouseout', (e) => {
    if (dismissed) return;
    if (e.relatedTarget === null && e.clientY <= 5) {
        banner.classList.add('show');
    }
});

document.addEventListener('mousemove', (e) => {
    if (!dismissed && e.clientY > 50 && banner.classList.contains('show')) {
        banner.classList.remove('show');
    }
});

closeBtn.addEventListener('click', () => {
    dismissed = true;
    banner.classList.remove('show');
});
