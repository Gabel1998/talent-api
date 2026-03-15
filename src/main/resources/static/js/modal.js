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

function setJsonContent(url, data) {
    const json = JSON.stringify(data, null, 2);
    body.innerHTML = `<div class="modal-url"><code>${url}</code></div><pre class="modal-json">${escapeHtml(json)}</pre>`;
}

function setMultiJsonContent(results) {
    body.innerHTML = results.map(r => {
        const json = JSON.stringify(r.data, null, 2);
        return `<div class="modal-url"><code>${r.url}</code></div><pre class="modal-json">${escapeHtml(json)}</pre>`;
    }).join('<div class="modal-divider"></div>');
}

function escapeHtml(str) {
    return str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

function close() {
    overlay.classList.remove('open');
}

export { open, setContent, setJsonContent, setMultiJsonContent, close };
