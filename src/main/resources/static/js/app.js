import * as I18n from './i18n.js';
import * as Documents from './documents.js';
import './projects.js';
import './exit-intent.js';
import './modal.js';

async function boot() {
    await I18n.load(I18n.getLang());
    Documents.init();
}

document.getElementById('lang-toggle').addEventListener('click', async () => {
    const next = I18n.getLang() === 'da' ? 'en' : 'da';
    await I18n.load(next);
});

boot();
