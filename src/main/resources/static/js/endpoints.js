import * as Modal from './modal.js';
import * as I18n from './i18n.js';

let talents = [];
let docsByTalent = {};

async function init() {
    // Accordion toggle
    const toggle = document.getElementById('endpoints-toggle');
    const panel = document.getElementById('endpoints-panel');
    toggle.addEventListener('click', () => {
        toggle.classList.toggle('open');
        panel.classList.toggle('open');
    });

    // Fetch all talent IDs and their documents
    try {
        talents = await fetch('talent').then(r => r.json());
        for (const t of talents) {
            const docs = await fetch(`talent/${t.id}/documents`).then(r => r.json());
            docsByTalent[t.id] = docs;
        }
    } catch { /* fallback to empty */ }

    // Endpoint buttons
    panel.querySelectorAll('.endpoint-btn').forEach(btn => {
        btn.addEventListener('click', () => openEndpoint(btn.dataset.path));
    });
}

async function openEndpoint(path) {
    const displayPath = `GET /${path}`;
    Modal.open(displayPath, I18n.t('loading'));

    try {
        if (path === 'talent') {
            const res = await fetch('talent').then(r => r.json());
            Modal.setJsonContent('talent', res);

        } else if (path === 'talent/{id}') {
            const results = [];
            for (const t of talents) {
                const res = await fetch(`talent/${t.id}`).then(r => r.json());
                results.push({ url: `talent/${t.id}`, data: res });
            }
            Modal.setMultiJsonContent(results);

        } else if (path === 'talent/{id}/documents') {
            const results = [];
            for (const t of talents) {
                const res = await fetch(`talent/${t.id}/documents`).then(r => r.json());
                results.push({ url: `talent/${t.id}/documents`, data: res });
            }
            Modal.setMultiJsonContent(results);

        } else if (path === 'talent/{id}/documents/{documentId}') {
            const results = [];
            for (const t of talents) {
                const docs = docsByTalent[t.id] || [];
                for (const doc of docs) {
                    const url = `talent/${t.id}/documents/${doc.id}`;
                    const res = await fetch(url).then(r => r.json());
                    results.push({ url, data: res });
                }
            }
            Modal.setMultiJsonContent(results);
        }
    } catch {
        Modal.setContent(I18n.t('load_error'));
    }
}

export { init };
