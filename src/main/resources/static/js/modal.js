const overlay = document.getElementById('modal-overlay');
const title = document.getElementById('modal-title');
const body = document.getElementById('modal-body');

document.getElementById('modal-close').addEventListener('click', close);
overlay.addEventListener('click', (e) => { if (e.target === overlay) close(); });
document.addEventListener('keydown', (e) => { if (e.key === 'Escape') close(); });

function open(heading, loadingText) {
    title.textContent = heading;
    body.innerHTML = `<div class="loading">${loadingText}</div>`;
    overlay.classList.add('open');
}

function setContent(text) {
    body.textContent = text;
}

function close() {
    overlay.classList.remove('open');
}

export { open, setContent, close };
