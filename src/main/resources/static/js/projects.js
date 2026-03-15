const toggle = document.getElementById('projects-toggle');
const panel = document.getElementById('projects-panel');

toggle.addEventListener('click', () => {
    toggle.classList.toggle('open');
    panel.classList.toggle('open');
});
