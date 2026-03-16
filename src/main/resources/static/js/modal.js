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
    body.style.whiteSpace = 'pre-wrap';
    body.textContent = text;
}

function urlBar(url) {
    return `<div class="modal-url"><a href="${url}" target="_blank" rel="noopener"><code>${url}</code></a></div>`;
}

function setJsonContent(url, data) {
    const json = JSON.stringify(data, null, 2);
    body.innerHTML = `${urlBar(url)}<pre class="modal-json">${formatJson(json)}</pre>`;
}

function setMultiJsonContent(results) {
    body.innerHTML = results.map(r => {
        const json = JSON.stringify(r.data, null, 2);
        return `${urlBar(r.url)}<pre class="modal-json">${formatJson(json)}</pre>`;
    }).join('<div class="modal-divider"></div>');
}

function formatJson(str) {
    return escapeHtml(str).replace(/\\n/g, '\n');
}

function escapeHtml(str) {
    return str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

function close() {
    overlay.classList.remove('open');
}

export { open, setContent, setJsonContent, setMultiJsonContent, close };
