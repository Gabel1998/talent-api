import * as Modal from './modal.js';
import * as I18n from './i18n.js';

let talentId = null;
let docId = null;

async function init() {
    // Accordion toggle
    const toggle = document.getElementById('endpoints-toggle');
    const panel = document.getElementById('endpoints-panel');
    toggle.addEventListener('click', () => {
        toggle.classList.toggle('open');
        panel.classList.toggle('open');
    });

    // Fetch IDs for real URLs
    try {
        const talents = await fetch('talent').then(r => r.json());
        if (talents.length) {
            talentId = talents[0].id;
            const docs = await fetch(`talent/${talentId}/documents`).then(r => r.json());
            if (docs.length) docId = docs[0].id;
        }
    } catch { /* IDs stay null, endpoints still work with placeholders */ }

    // Endpoint buttons
    panel.querySelectorAll('.endpoint-btn').forEach(btn => {
        btn.addEventListener('click', () => openEndpoint(btn.dataset.path));
    });
}

function resolvePath(path) {
    let resolved = path;
    if (talentId) resolved = resolved.replace('{id}', talentId);
    if (docId) resolved = resolved.replace('{documentId}', docId);
    return resolved;
}

async function openEndpoint(path) {
    const resolved = resolvePath(path);
    const displayPath = `GET /${path}`;
    Modal.open(displayPath, I18n.t('loading'));

    try {
        const res = await fetch(resolved);
        if (!res.ok) {
            Modal.setContent(`${res.status} ${res.statusText}`);
            return;
        }
        const data = await res.json();
        Modal.setJsonContent(resolved, data);
    } catch {
        Modal.setContent(I18n.t('load_error'));
    }
}

export { init };
