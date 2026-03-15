import * as I18n from './i18n.js';
import * as Modal from './modal.js';

const DOC_IDS = ['d0c00001-0000-0000-0000-000000000001', 'd0c00002-0000-0000-0000-000000000002'];

let talentId = null;

async function init() {
    try {
        const talents = await fetch('talent').then(r => r.json());
        if (!talents.length) return;

        talentId = talents[0].id;
        render();
    } catch {
        showError();
    }
}

async function render() {
    const docs = await fetch(`talent/${talentId}/documents`).then(r => r.json());
    const list = document.getElementById('doc-list');
    list.innerHTML = '';

    docs.filter(d => DOC_IDS.includes(d.id)).forEach(doc => {
        const name = I18n.getDocName(doc.id, doc.name);
        const btn = document.createElement('button');
        btn.className = 'doc-btn';
        btn.dataset.docId = doc.id;
        btn.innerHTML = `${name} <span class="doc-btn-arrow">\u2192</span>`;
        btn.addEventListener('click', () => openDocument(doc.id));
        list.appendChild(btn);
    });
}

async function openDocument(docId) {
    const name = I18n.getDocName(docId, docId);
    Modal.open(name, I18n.t('loading'));

    try {
        const doc = await fetch(`talent/${talentId}/documents/${docId}`).then(r => r.json());
        Modal.setContent(doc.content);
    } catch {
        Modal.setContent(I18n.t('load_error'));
    }
}

function showError() {
    document.getElementById('doc-list').innerHTML =
        `<div class="doc-btn" style="justify-content:center;color:#6B7280;cursor:default;">${I18n.t('load_error')}</div>`;
}

export { init };
