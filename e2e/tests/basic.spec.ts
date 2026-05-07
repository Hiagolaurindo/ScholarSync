import { test, expect } from '@playwright/test';
test('participante acessa login', async ({ page }) => { await page.goto('/login'); await expect(page.getByText('Login')).toBeVisible(); });
test('usuario visualiza eventos', async ({ page }) => { await page.goto('/events'); await expect(page.getByText('Eventos')).toBeVisible(); });
test('admin acessa painel', async ({ page }) => { await page.goto('http://localhost:4200/admin/login'); await expect(page.locator('body')).toBeVisible(); });
