import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginUsuarioPage } from './login-usuario.page';

describe('LoginUsuarioPage', () => {
  let component: LoginUsuarioPage;
  let fixture: ComponentFixture<LoginUsuarioPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginUsuarioPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
