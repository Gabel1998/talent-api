let translations = {};
let currentLang = localStorage.getItem('talent-api-lang') || 'da';

const DOC_NAMES = {
    'Motivationsbrev': 'doc_motivationsbrev',
    'CV': 'doc_cv'
};

async function load(lang) {
    const res = await fetch(`data/i18n/${lang}.json`);
    translations = await res.json();
    currentLang = lang;
    localStorage.setItem('talent-api-lang', lang);
    document.documentElement.lang = lang;
    apply();
    window.dispatchEvent(new CustomEvent('langchange', { detail: lang }));
}

function apply() {
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.dataset.i18n;
        if (translations[key]) el.textContent = translations[key];
    });

    document.querySelectorAll('.doc-btn[data-doc-id]').forEach(btn => {
        const name = btn.dataset.docName;
        const key = DOC_NAMES[name];
        if (key && translations[key]) {
            btn.innerHTML = `${translations[key]} <span class="doc-btn-arrow">\u2192</span>`;
        }
    });

    const toggle = document.getElementById('lang-toggle');
    if (toggle) toggle.textContent = currentLang === 'da' ? 'EN' : 'DA';
}

function t(key) {
    return translations[key] || key;
}

function getLang() {
    return currentLang;
}

function getDocName(name, fallback) {
    const key = DOC_NAMES[name];
    return key && translations[key] ? translations[key] : fallback;
}

export { load, t, getLang, getDocName, DOC_NAMES };
